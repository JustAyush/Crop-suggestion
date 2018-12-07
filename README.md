# Crop-suggestion
This is an app that suggests an appropriate crop given that the soil parameters are provided. In addition, one can post their agricultural product and other can make a request for the product.
More about this project at https://drive.google.com/open?id=1pYyWf8aDk4QXryEvU2dEQpauKEsYCsj6


It has been arranged in four separate folder for convenience.

server-side (flask)
  KNN algorithm has been implemented in order to classify crops and deployed using python micro-framework flask. It's been programmed 
  such that it takes in JSON request from the app or nodeMCU and replies with an appropriate crop.
  
nodeMCU
  nodeMCU to read the sensor data (moisture, temperature and humidity) and send request to the flask server.
  
app (Android Studio)
  The app itself has been developed in Android Studio IDE
  
server-side (php)
  SQL queries, authentication and other server-side development has been handled with php.
