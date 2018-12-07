#include <ESP8266WiFi.h>
#include <WiFiClient.h> 
#include <ESP8266WebServer.h>
#include <ESP8266HTTPClient.h>
#include <ArduinoJson.h>
#include <String.h>
#include <dht.h>


const char *ssid = "ES_8262";  //ENTER YOUR WIFI SETTINGS
const char *password = "37600000";

bool state = false;

ESP8266WebServer server(80);

const int sensor_pin = A0;
dht DHT;
#define DHT11_PIN 14

void setup() {
  // put your setup code here, to run once:
  delay(1000);
  Serial.begin(115200);
  WiFi.mode(WIFI_OFF);        //Prevents reconnection issue (taking too long to connect)
  delay(1000);
  WiFi.mode(WIFI_STA);        //This line hides the viewing of ESP as wifi hotspot
  
  WiFi.begin(ssid, password);     //Connect to your WiFi router
  Serial.println("");
 
  Serial.print("Connecting");
  // Wait for connection
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("");
  Serial.print("Connected to ");
  Serial.println(ssid);
  Serial.print("IP address: ");
  Serial.println(WiFi.localIP());

  
  server.on("/body",handleBody);
  server.on("/bodyAndroid",handleBodyAndroid);
  server.begin();
  Serial.println("Server Listening");

}

void loop() {
  // put your main code here, to run repeatedly:
   
  server.handleClient();

  if (state==1){
    Serial.println("It's ready.");

   if(WiFi.status()== WL_CONNECTED){   //Check WiFi connection status
   StaticJsonBuffer<300> JSONbuffer;   //Declaring static JSON buffer
   JsonObject& JSONencoder = JSONbuffer.createObject();  

   int moisture_percentage;

   moisture_percentage = ( 100.00 - ( (analogRead(sensor_pin)/1023.00) * 100.00 ) );

   Serial.print("Soil Moisture(in Percentage) = ");
   Serial.print(moisture_percentage);
   Serial.println("%");

   int chk = DHT.read11(DHT11_PIN);
   Serial.print("Temperature = ");
   Serial.println(DHT.temperature);
   Serial.print("Humidity = ");
   Serial.println(DHT.humidity);
        
   JSONencoder["moisture"] = "45";
   JSONencoder["temperature"] = "55";
   JSONencoder["humidity"] = "65";
              
   char JSONmessageBuffer[300];
   JSONencoder.prettyPrintTo(JSONmessageBuffer, sizeof(JSONmessageBuffer));
   Serial.println(JSONmessageBuffer);


   HTTPClient http;    //Declare object of class HTTPClient
 
   http.begin("http://192.168.0.9:8090/yoESP");      //Specify request destination
   http.addHeader("Content-Type", "application/json");  //Specify content-type header
 
   int httpCode = http.POST(JSONmessageBuffer);   //Send the request
   //String payload = http.getString();                  //Get the response payload
 
   Serial.println(httpCode);   //Print HTTP return code
   //Serial.println(payload);    //Print request response payload
 
   http.end();  //Close connection
 
  }else{
 
    Serial.println("Error in WiFi connection");   
 
 }

    
    state = false;
    }
  
}

void handleBody(){
     if (server.hasArg("plain")== false){ //Check if body received
 
            server.send(200, "text/plain", "Body not received");
            return;   
      }
 
      String message = server.arg("plain");
             message += "\n";
      server.send(200, "text/plain", message);
      Serial.println(state); // here
      Serial.println(message);
      state = true;
      Serial.println(state);
      
  }
  
  void handleBodyAndroid(){
   StaticJsonBuffer<200> jsonBuffer;
   JsonObject& agro=jsonBuffer.createObject();
   /*agro["temperature"]=DHT.humidity;
   agro["humidity"]=DHT.temperature;
   agro["moisture"]=moisture_percentage;  */ 
   int moisture_percentage;

   moisture_percentage = ( 100.00 - ( (analogRead(sensor_pin)/1023.00) * 100.00 ) );

   Serial.print("Soil Moisture(in Percentage) = ");
   Serial.print(moisture_percentage);
   Serial.println("%");

   int chk = DHT.read11(DHT11_PIN);
   Serial.print("Temperature = ");
   Serial.println(DHT.temperature);
   Serial.print("Humidity = ");
   Serial.println(DHT.humidity);
   agro["temperature"]= DHT.temperature;
   agro["humidity"]= DHT.humidity;
   agro["moisture"]= moisture_percentage;
   char crops[100];
   agro.prettyPrintTo(crops, sizeof(crops));
   server.send(200, "text/plain", crops);
      
  }
  
  /**
   NodeMCU-Sensors connections:
   Moisture sensor
   A0->A0, Vcc->3V3 Gnd->Gnd
   DHT11 sensor
   out->D5, Vcc->3V3 Gnd->Gnd
  **/
