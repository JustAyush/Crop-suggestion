from flask import Flask, render_template, request, flash, redirect, url_for
from flask_wtf import Form
from wtforms import DecimalField, SubmitField, validators, ValidationError, SubmitField
import pickle
import requests
import json
from flask_bootstrap import Bootstrap
from flask_wtf import FlaskForm
from wtforms import StringField, PasswordField, BooleanField
from wtforms.validators import InputRequired, Email, Length
from flask_sqlalchemy import SQLAlchemy
from werkzeug.security import generate_password_hash, check_password_hash
from flask_login import LoginManager, UserMixin, login_user, login_required, logout_user, current_user


app = Flask(__name__)
app.secret_key = 'development key'

app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///F:\\FromDiskG\\Webtest\\JustChecking\\database.db'
Bootstrap(app)
db = SQLAlchemy(app)
login_manager = LoginManager()
login_manager.init_app(app)
login_manager.login_view = 'login'


loaded_model = pickle.load(open('g.sav', 'rb'))

class User(UserMixin, db.Model):
	id = db.Column(db.Integer, primary_key=True)
	username = db.Column(db.String(15), unique=True)
	email = db.Column(db.String(50), unique=True)
	password = db.Column(db.String(80))
	

@login_manager.user_loader
def load_user(user_id):
    return User.query.get(int(user_id))

moisture_ob = 0
temperature_ob = 0
humidity_ob = 0

class InputForm(Form):
    moisture = DecimalField("Moisture: ", [validators.Required()])
    temperature = DecimalField("Temperature (%): ",[validators.Required()])
    humidity = DecimalField("Humidity (%): ",[validators.Required()])

			
class LoginForm(FlaskForm):
	username = StringField('Username', validators=[InputRequired(), Length(min=4, max=15)])
	password = PasswordField('Password', validators=[InputRequired(), Length(min=8, max=80)])
	remember = BooleanField('Remember me')
	
class RegisterForm(FlaskForm):
	email = StringField('Email', validators=[InputRequired(), Email(message='Invalid email'), Length(max=50)])
	username = StringField('Username', validators=[InputRequired(), Length(min=4, max=15)])
	password = PasswordField('Password', validators=[InputRequired(), Length(min=8, max=80)])	
	
	
@app.route('/yoESP', methods = ['POST'])
def ESPHandler():
	global moisture_ob
	global temperature_ob	
	global humidity_ob	
	content = request.get_json()
	moisture_ob = content['moisture']
	temperature_ob = content['temperature']
	humidity_ob = content['humidity']
	return "NodeMCU"
		

@app.route('/yoAndroid', methods = ['POST'])
def AndroidHandler():	
	temperature = request.form.get('temperature')
	moisture = request.form.get('moisture')
	humidity = request.form.get('humidity')
	
	input_values = [[(moisture), (temperature), (humidity)]]
	result = loaded_model.predict(input_values)
	if result == 0:
		crop = 'Corn'
	elif result == 1:
		crop = 'Maize'
	elif result == 2:
		crop = 'Potato'
	elif result == 3:
		crop = 'Rice'
	elif result == 4:
		crop = 'Sugarcane'
	elif result == 5:
		crop = 'Tomato'
	else:
		crop = 'Wheat'
	
	return crop


@app.route('/', methods =['GET', 'POST'])
@login_required
def home():
    form = InputForm(request.form)
	#moisture = temperature = humidity = 70
    if request.method == 'POST':
        if form.validate() == False:
            flash('All fields are required.')
            return render_template('form.html', form = form)
        else:
            moisture = request.form['moisture']
            temperature = request.form['temperature']
            humidity = request.form['humidity']

            input_values = [[(moisture), (temperature), (humidity)]]
            result = loaded_model.predict(input_values)
            if result == 0:
                crop = 'Corn'
            elif result == 1:
                crop = 'Maize'
            elif result == 2:
                crop = 'Potato'
            elif result == 3:
                crop = 'Rice'
            elif result == 4:
                crop = 'Sugarcane'
            elif result == 5:
                crop = 'Tomato'
            else:
                crop = 'Wheat'
            return render_template('thatCrop.html', crop = crop)
    if request.method == 'GET':
        return render_template('form.html', form = form, moisture_ob = moisture_ob, temperature_ob = temperature_ob, humidity_ob = humidity_ob)

@app.route('/readycode', methods =['POST','GET'])
def ready():
	if request.method =='POST':
		#r = requests.post("http://192.168.100.26:80/body")
		url = 'http://192.168.100.26:80/body'
		payload={'fruit':'apple'}
		headers = {'content-type': 'application/json'}
		r = requests.post(url, data = json.dumps(payload), headers=headers)
		return redirect(url_for('home'))
	else:
		return 'No'
		
		
@app.route('/landingpage')
@login_required
def landingpage():
	return render_template('landingpage.html')

@app.route('/login', methods = ['GET','POST'])
def login():
	form = LoginForm()
	
	if form.validate_on_submit():
		user = User.query.filter_by(username=form.username.data).first()
		if user:
			if check_password_hash(user.password, form.password.data):
				login_user(user, remember=form.remember.data)
				return redirect(url_for('home'))
			else: 
				flash('Invalid Username')
				return redirect(url_for('login'))
				
		else:
			flash('Invalid Password')
			return redirect(url_for('login'))
			
	return render_template('login.html', form=form)

@app.route('/signup', methods = ['GET','POST'])
def signup():
	form = RegisterForm()
	
	if form.validate_on_submit():
		hashed_password = generate_password_hash(form.password.data, method='sha256')
		new_user = User(username=form.username.data, email=form.email.data, password=hashed_password)
		db.session.add(new_user)
		db.session.commit()
		return '<h1> New user has been created</h1>'
	return render_template('signup.html', form=form)

@app.route('/logout')
@login_required
def logout():
    logout_user()
    return redirect(url_for('landingpage'))
		
		
if __name__ == "__main__":	
	app.run(host = '0.0.0.0', port = 8090)
	