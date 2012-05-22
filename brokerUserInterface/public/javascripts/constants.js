//Common constants 
var FAKE_SECRET_KEY="aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
var SECRET_KEY="secretkey";
var USERNAME="username";
var SUCCESS=0;
var FAILURE="Failure";
var PASSWORD="password";
var EMAIL="email";
//Dictionary constants associated with device profile
var DEVICE_ARRAY="devicelist";
var DEVICE_PROFILE="deviceprofile";
var DEVICE_NAME="name";
var DEVICE_LOCATION="location";
var DEVICE_SENSORS="sensors";
var DEVICE_ACTUATORS="actuators";
var DEVICE_SENSOR_CHANNELS="channels";
var DEVICE_SENSOR_NAME="name";
var DEVICE_SENSOR_ID="id";
var DEVICE_SENSOR_CHANNEL_NAME="name";
var DEVICE_SENSOR_CHANNEL_TYPE="type";
var DEVICE_SENSOR_CHANNEL_UNIT="unit";
var DEVICE_SENSOR_CHANNEL_SAMPLING_PERIOD="samplingperiod";
var DEVICE_ACTUATOR_NAME="name";
var DEVICE_IP="IP";
var DEVICE_TAGS="tags";
var DEVICE_LATITUDE="latitude";
var DEVICE_LONGITUDE="longitude";
var DEVICE_STRUCTURE_NUMBER_OF_ROWS_PER_SENSOR=2;

//Dictionary constants associated with wavesegment
var WAVESEGMENT_ARRAY="wavesegmentArray";
var WAVESEGMENT_DATA="data";
var WAVESEGMENT_CHANNELS="channels";
var WAVESEGMENT_SENSOR_NAME="sname";
var WAVESEGMENT_CHANNEL_NAME="cname";
var WAVESEGMENT_SAMPLING_INTERVAL="sinterval";
var WAVESEGMENT_TIMESTAMP="timestamp";
var WAVESEGMENT_READINGS="readings";

//Dictionary constants associated with query
var QUERY_CONDITIONS="conditions";
var QUERY_CONDITIONS_FROM_TIME="fromtime";
var QUERY_CONDITIONS_TO_TIME="totime";
var QUERY_DEVICE_NAME="devicename";
var QUERY_SENSOR_NAME="sensorname";
var QUERY_USER_NAME="username";

//Dictionary constants associated with Chart Series
var CHART_SERIES_NAME="name";
var CHART_SERIES_DATA="data";


//Class Types associated with various HTML/Object elements
// NB :These elements are hardcoded into the HTML styling and are presented 
//here for simplicity
//#############  DEVICE  #################################
var CLASS_DEVICE_SENSOR_CHANNEL_NAME="labName";
var CLASS_DEVICE_SENSOR_CHANNEL_UNIT="labUnit";
var CLASS_DEVICE_SENSOR_CHANNEL_TYPE="labType";
var CLASS_DEVICE_SENSOR_CHANNEL_SAMPLING_PERIOD="labSampling";

//Dictionary constants associated with Response
var RESPONSE_STATUS_CODE="statuscode";
var RESPONSE_MESSAGE="message";
var RESPONSE_API_NAME="apiname";

//ID Tags associated with HTML elements
var ID_START_DATE_TIME_DISPLAY="#start_date_time";
var ID_END_DATE_TIME_DISPLAY="#end_date_time";

//Validations
var MIN_LENGTH_ACTUATOR_NAME=2;
var MAX_LENGTH_ACTUATOR_NAME=20;
var MIN_LENGTH_SENSOR_NAME=2;
var MAX_LENGTH_SENSOR_NAME=20;
var MIN_LENGTH_SENSOR_ID=1;
var MAX_LENGTH_SENSOR_ID=3;
var MIN_LENGTH_CHANNEL_NAME=2;
var MAX_LENGTH_CHANNEL_NAME=20;
var MIN_LENGTH_CHANNEL_UNIT=2;
var MAX_LENGTH_CHANNEL_UNIT=20;
var MIN_LENGTH_CHANNEL_TYPE=2;
var MAX_LENGTH_CHANNEL_TYPE=20;
var MIN_LENGTH_CHANNEL_SAMPLING_PERIOD=2;
var MAX_LENGTH_CHANNEL_SAMPLING_PERIOD=20;
var MIN_LENGTH_DEVICE_NAME=2;
var MAX_LENGTH_DEVICE_NAME=20;
var MIN_LENGTH_DEVICE_TAGS=2;
var MAX_LENGTH_DEVICE_TAGS=20;
var MIN_LENGTH_DEVICE_IP=7;
var MAX_LENGTH_DEVICE_IP=15;
var MIN_LENGTH_DEVICE_LOCATION=2;
var MAX_LENGTH_DEVICE_LOCATION=20;
var MIN_LENGTH_USERNAME=8;
var MIN_LENGTH_PASSWORD=6;
var MIN_VALUE_CHANNEL_SAMPLING_PERIOD=1;
var MAX_VALUE_CHANNEL_SAMPLING_PERIOD=100;
var MIN_VALUE_SENSOR_ID=1;
var MAX_VALUE_SENSOR_ID=100;

//Dictionary elements associated with Repository information
var REPOSITORY_NAME="name";
var REPOSITORY_URL="URL";
var REPOSITORY_LIST="repolist";
var REPOSITORY_KEY="repokey";




//URL's ------------------------------------------------------------------------
//Note that this needs to be modified when on LAN etc to http://192.168.1.122:9000
//############# DEVICE  ###################################
var URL_UI_SERVER="http://muc.iiitd.com:9003/";

var URL_LOGIN_USER=URL_UI_SERVER+"login";
var URL_REGISTER_USER=URL_UI_SERVER+"register";
var URL_ADD_DEVICE=URL_UI_SERVER+"adddevice";
var URL_DELETE_DEVICE=URL_UI_SERVER+"deletedevice";
var URL_REGISTER_USER=URL_UI_SERVER+"register";
var URL_QUERY_DATA=URL_UI_SERVER+"querydata";
var URL_LIST_ALL_DEVICES=URL_UI_SERVER+"listalldevices";
var URL_LIST_ALL_REPOSITORIES=URL_UI_SERVER+"listallrepositories";
var URL_HOME=URL_UI_SERVER+"home";
var URL_DEVICES=URL_UI_SERVER+"device"
var URL_LOGOUT_USER=URL_UI_SERVER+"logout";
var URL_VISUALIZATION=URL_UI_SERVER+"display2";
var URL_REPOSITORY_INFO=URL_UI_SERVER+"repository";
var URL_GET_REPOSITORY_INFO=URL_UI_SERVER+"getrepositoryinfo";
var URL_GENERATE_SECRET_KEY=URL_UI_SERVER+"generatesecretkey";
var URL_REGISTER_REPOSITORY=URL_UI_SERVER+"registerrepository";







/*
 * Test Objects
 * This sections contains all the test objects(which contain sample data formats)
 */

//This is a sample response on invoking the list all devices to the broker
var SAMPLE_LIST_ALL_DEVICES_QUERY_RESPONSE={
    "devices": [
        {
            "deviceprofile": {
                "sensors": [
                    {
                        "name": "Presence",
                        "channels": [
                            {
                                "name": "pir",
                                "type": "b"
                            },
                            {
                                "name": "analog_pir",
                                "type": "int"
                            }
                        ]
                    }
                ],
                "name": "iiitNode",
                "location": "PhDroom"
            }
        },
        {
            "deviceprofile": {
                "sensors": [
                    {
                        "name": "Position",
                        "channels": [
                            {
                                "name": "AccelerometerX",
                                "type": "int"
                            },
                            {
                                "name": "AccelerometerY",
                                "type": "int"
                            }
                        ]
                    },
                    {
                        "name": "Altitude",
                        "channels": [
                            {
                                "name": "GPS",
                                "type": "int"
                            }
                        ]
                    }
                ],
                "name": "SamyNode",
                "location": "Delhi"
            }
        }
    ]
};

var SAMPLE_QUERY_DATA_QUERY_RESPONSE={
    "wavesegmentArray": [
        {
            "data": {
                "devicename": "new device",
                "timestamp": 1234567890,
                "sname": "new sensor",
                "sinterval": 1,
                "channels": [
                    {
                        "cname": "channel1",
                        "unit": "F",
                        "readings": [
                            1,
                            2,
                            3,
                            4,
                            5,
                            6,
                            7,
                            8,
                            9,
                            10
                        ]
                    },
                    {
                        "cname": "channel2",
                        "units": "C",
                        "readings": [
                            10,
                            11,
                            12,
                            13,
                            14,
                            15,
                            16,
                            17,
                            18,
                            19
                      
                        ]
                    },
                    {
                        "cname": "channel3",
                        "units": "C",
                        "readings": [
                            20,
                            21,
                            2,
                            0,
                            4,
                            7,
                            8,
                            2,
                            5,
                            0
                        ]
                    }
                ]
            }
        },
        {
            "data": {
                "devicename": "new device",
                "timestamp": 1234567900,
                "sname": "new sensor",
                "sinterval": 1,
                "channels": [
                    {
                        "cname": "channel1",
                        "unit": "F",
                        "readings": [
                            5,
                            6,
                            7,
                            8,
                            1,
                            2,
                            8,
                            2,
                            0,
                            2
                        ]
                    },
                    {
                        "cname": "channel2",
                        "units": "C",
                        "readings": [
                            1,
                            2,
                            6,
                            6,
                            2,
                            4,
                            3,
                            0,
                            0,
                            0
                        ]
                    },
                    {
                        "cname": "channel3",
                        "units": "C",
                        "readings": [
                            6,
                            2,
                            0,
                            0,
                           4,
                            5,
                            0,
                            6,
                            5,
                            0
                        ]
                    }
                ]
            }
        }
    ]
};