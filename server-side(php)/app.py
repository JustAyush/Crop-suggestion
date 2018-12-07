#!C:\ProgramData\Anaconda3\python.exe
import cgitb
cgitb.enable()
print("Content-Type: text/html;charset=utf-8")
print()
from flask import Flask
app = Flask(__name__)

@app.route('/templates/index.html')
def hello_world():
    return 'Hello, World!'

app = Flask(__name__)
print ("bye")
print(hello_world())

'''
app = Flask(__name__)
app.secret_key = 'development key'

loaded_model = pickle.load(open('g.sav', 'rb'))
print(loaded_model)


class InputForm(Form):
    moisture = DecimalField("Moisture: ", [validators.Required()])
    temperature = DecimalField("Temperature (%): ",[validators.Required()])
    humidity = DecimalField("Humidity (%): ",[validators.Required()])

#import urllib
import re

print ("we will try to open this url, in order to get IP Address")


'''

'''

loaded_model = pickle.load(open('g.sav', 'rb'))
result = loaded_model.predict([[67,52,76]])
print(result)

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

print(crop)
'''
