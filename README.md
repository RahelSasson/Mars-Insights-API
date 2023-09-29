# Mars-Insights-API

#Java project utilizing the NASA Mars Insights API to display weather information on mars 

NASA’s InSight Mars lander records weather information on the surface of Mars at Elysium Planitia. A smooth plain near the Mars equator. The API is maintained and provided by NASA Jet Propulsion Laboratory and Cornell University. 
Sensors on the spacecraft are temporarily turned off to conserve energy however the most recent recorded information is still avaible for use.
 
This API provides weather information data recorded by the spacecraft for the last seven available *Sols*. 
A Sol is what a Martian day is called.

img or video >>> 



API URL: https://mars.nasa.gov/rss/api/?feed=weather&category=insight_temperature&feedtype=json&ver=1.0
Documentation: https://api.nasa.gov/assets/insight/InSight%20Weather%20API%20Documentation.pdf

Example of Reponse code for a single Sol: 

...
`{
 "675": {
    "AT": {
      "av": -62.314, 
      "ct": 177556, 
      "mn": -96.872, 
      "mx": -15.908
    }, 
    "First_UTC": "2020-10-19T18:32:20Z", 
  "HWS": {
      "av": 7.233, 
      "ct": 88628, 
      "mn": 1.051, 
      "mx": 22.455
    }, 
    "Last_UTC": "2020-10-20T19:11:55Z", 
    
    "Month_ordinal": 10, 
    "Northern_season": "early winter", 
    "PRE": {
      "av": 750.563, 
      "ct": 887776, 
      "mn": 722.0901, 
      "mx": 768.791
    }, 
    "Season": "fall", 
    "Southern_season": "early summer", 
    
    "WD": {
      "0": {
        "compass_degrees": 0.0, 
        "compass_point": "N", 
        "compass_right": 0.0, 
        "compass_up": 1.0, 
        "ct": 254
      }, 
      
      "1": {
        "compass_degrees": 22.5, 
        "compass_point": "NNE", 
        "compass_right": 0.382683432365, 
        "compass_up": 0.923879532511, 
        "ct": 1
      }, 
      
      "10": {
        "compass_degrees": 225.0, 
        "compass_point": "SW", 
        "compass_right": -0.707106781187, 
        "compass_up": -0.707106781187, 
        "ct": 8618
      }, 
      
      "11": {
        "compass_degrees": 247.5, 
        "compass_point": "WSW", 
        "compass_right": -0.923879532511, 
        "compass_up": -0.382683432365, 
        "ct": 1912
      }, 
      
      "12": {
        "compass_degrees": 270.0, 
        "compass_point": "W", 
        "compass_right": -1.0, 
        "compass_up": -0.0, 
        "ct": 19517
      }, 
      
      "13": {
        "compass_degrees": 292.5, 
        "compass_point": "WNW", 
        "compass_right": -0.923879532511, 
        "compass_up": 0.382683432365, 
        "ct": 30283
      }, 
      
      "14": {
        "compass_degrees": 315.0, 
        "compass_point": "NW", 
        "compass_right": -0.707106781187, 
        "compass_up": 0.707106781187, 
        "ct": 25962
      }, 
      
      "15": {
        "compass_degrees": 337.5, 
        "compass_point": "NNW", 
        "compass_right": -0.382683432365, 
        "compass_up": 0.923879532511, 
        "ct": 2014
      }, 
      
      "9": {
        "compass_degrees": 202.5, 
        "compass_point": "SSW", 
        "compass_right": -0.382683432365, 
        "compass_up": -0.923879532511, 
        "ct": 67
      }, 
      
      "most_common": {
        "compass_degrees": 292.5, 
        "compass_point": "WNW", 
        "compass_right": -0.923879532511, 
        "compass_up": 0.382683432365, 
        "ct": 30283
      }
    }
  }, `
 
  ...
  

Key-Per Sol   Description
AT >        Object; per-Sol atmospheric temperature sensor summary data
HWS >       Object; per-Sol horizontal wind speed sensor summary data
PRE >       Object; per-Sol atmospheric pressure sensor summary data
WD >        Object; per-Sol wind direction sensor summary data
Season >    String; per-Sol season on Mars; one of [“winter”, “spring”, “summer”, “fall”]
First_UTC > Time of first datum, of any sensor, for the Sol (UTC; YYYY-MM-DDTHH:MM:SSZ)
Last_UTC >  Time of last datum, of any sensor, for the Sol (UTC; YYYY-MM-DDTHH:MM:SSZ)

av >        Average of samples over the Sol (°F for AT; m/s for HWS; Pa for PRE)
ct >        Total number of recorded samples over the Sol
mn >        Minimum data sample over the sol (same units as av)
mx >        Maximum data sample over the sol (same units as av)
