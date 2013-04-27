/*
# * Copyright (c) 2012, Indraprastha Institute of Information Technology,
# * Delhi (IIIT-D) and The Regents of the University of California.
# * All rights reserved.
# *
# * Redistribution and use in source and binary forms, with or without
# * modification, are permitted provided that the following conditions
# * are met:
# * 1. Redistributions of source code must retain the above copyright
# * notice, this list of conditions and the following disclaimer.
# * 2. Redistributions in binary form must reproduce the above
# * copyright notice, this list of conditions and the following
# * disclaimer in the documentation and/or other materials provided
# * with the distribution.
# * 3. Neither the names of the Indraprastha Institute of Information
# * Technology, Delhi and the University of California nor the names
# * of their contributors may be used to endorse or promote products
# * derived from this software without specific prior written permission.
# *
# * THIS SOFTWARE IS PROVIDED BY THE IIIT-D, THE REGENTS, AND CONTRIBUTORS
# * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
# * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
# * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE IIITD-D, THE REGENTS
# * OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
# * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
# * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
# * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
# * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
# * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
# * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
# * SUCH DAMAGE.
# *
# *
#-------------------------------------------------------------------------------
*/
//Common constants 
var FAKE_SECRET_KEY="aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
var FAKE_DEVICE_NAME="aaaaaaaaaaaaaaaaaaa";
var FAKE_GUARD_RULE_NAME="aaaaa";
var SECRET_KEY="secretkey";
var USERNAME="username";
var SUCCESS=0;
var NO_TASKLET_FOUND = 50;
var NO_ACTUATION_REQUEST_FOUND = 60;
var ACTUATION_REQUEST_LISTFAILED = 61;
var TASKLET_NOT_CANCELED = 54;
var NO_SHARED_DEVICE = 33;
var NO_DEVICE_FOUND = 32;
var NO_GUARD_RULE_FOUND = 40;
var NO_GUARD_RULEASSOC_FOUND = 41;
var NO_REGISTERED_VPDS = 29;
var FAILURE="Failure";
var PASSWORD="password";
var EMAIL="email";
var USERTYPE="usertype";
var ORIGIN="origin";

//Dictionary constants associated with user profile
var USERNAME_ARRAY="usernames";     //For VPDS
var USERNAMEARRAY="usernamelist";     // For Broker

//Dictionary constants associated with device profile
var DEVICE_ARRAY="devicelist";
var DEVICE_TEMPLATE_ARRAY="templatelist";
var DEVICE_ACTLIST_ARRAY="deviceActuationRequestList";
var GUARD_RULE_ARRAY="guardrulelist";
var GUARD_RULE_ASSOCARRAY="associationlist";

var DEVICE_PROFILE="deviceprofile";
var DEVICE_NAME="devicename";
var DEVICE_TEMPLATE_NAME="templatename";
var DEVICE_LOCATION="location";
var DEVICE_SENSORS="sensors";
var DEVICE_CHANNELS = "channels";
var DEVICE_ACTUATORS="actuators";
var DEVICE_SENSOR_CHANNELS="channels";
var DEVICE_SENSOR_NAME="name";
var DEVICE_SENSOR_ID="sid";
var DEVICE_SENSOR_CHANNEL_NAME="name";
var DEVICE_SENSOR_CHANNEL_TYPE="type";
var DEVICE_SENSOR_CHANNEL_UNIT="unit";
var DEVICE_SENSOR_CHANNEL_SAMPLING_PERIOD="samplingperiod";
var DEVICE_ACTUATOR_NAME="name";
var DEVICE_ACTUATOR_ID="aid";
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
var QUERY_SENSOR_ID="sensorid";
var QUERY_USER_NAME="username";
var QUERY_IS_INTERACTIVE="interactive";

//Dictionary constants associated with actuation
var ACTUATION_SECRETKEY="secretkey";
var ACTUATION_TASKLETNAME="taskletname";
var ACTUATION_DESC="desc";
var ACTUATION_PARAM="param";
var ACTUATION_INPUT="input";
var ACTUATION_WHEN="when";
var ACTUATION_EXECUTE_SCRIPT="execute";

var ACTUATION_TASKLETID="taskletId";

//Dictionary constants associated with tasklets
var TSK_SECRETKEY="secretkey";
var TSK_TASKLETNAME="taskletname";
var TSK_DESC="desc";
var TSK_PARAM="param";
var TSK_INPUT="input";
var TSK_WHEN="when";
var TSK_EXECUTE="execute";

var TSK_TASKLETID="taskletId";

//Dictionary constants associated with guard rules
var GUARDRULE="rule";
var GUARDRULE_NAME="name";
var GUARDRULE_DESC="description";
var GUARDRULE_TARGETOP="targetOperation";
var GUARDRULE_PRIORITY="priority";
var GUARDRULE_CONDITION="condition";
var GUARDRULE_ACTION="action";

//Dictionary constants associated with guard rules
var GUARDASSOCRULE_NAME="rulename";
var GUARDASSOCRULE_DEVICENAME="devicename";
var GUARDASSOCRULE_SENSORNAME="sensorname";
var GUARDASSOCRULE_ACTUATORNAME="actuatorname";
var GUARDASSOCRULE_ACTUATORID="actuatorid";
var GUARDASSOCRULE_SENSORID="sensorid";

var ASSOCRULE_DEVICE_ARRAY="devicesArray";
var ASSOCRULE_SENSOR_ARRAY="sensorsArray";
var ASSOCRULE_ACTUATOR_ARRAY="actuatorsArray";
var ASSOCRULE_REMOVE_ARRAY="removeArray";

//Dictionary constants associated with Device Share
var SHARE="share";
var SHARE_DEVICE_NAME="devicename";
var SHARE_SENSOR_NAME="sensorname";
var SHARE_SENSOR_ID="sensorid";
var SHARE_ACTUATOR_NAME="actuatorname";
var SHARE_ACTUATOR_ID="actuatorid";
var SHARE_USERNAME="username";
var READ="read";
var WRITE="write";

//Dictionary constants associated with View Shared Devices with users
var VIEW_SHARED="shared";
var VIEW_SHARE_VONAME="voname";
var VIEW_SHARE_VPDS_NAME="vpdsname";
var VIEW_DEVICENAME="devicename";
var VIEW_SENSORS="sensors";
var VIEW_ACTUATORS="actuators";

//Dictionary constants associated with VPDS registration
var VPDS_NAME="vpdsname";
var VPDS_KEY="ownerkey";
var VPDS_URL="vpdsURL";

//Dictionary constants associated with VPDS management
var VPDSOWNER_KEY="vpdsownerkey";
var VPDS_LIST_ARRAY="vpdslist";

//Dictionary constants associated with Chart Series
var CHART_SERIES_NAME="name";
var CHART_SERIES_DATA="data";

var QUERY_DEVICE_ARRAY = "devicesArray";
var QUERY_SENSOR_ARRAY = "sensorsArray";
var QUERY_DEVICE = "device";
var QUERY_SENSOR = "sensor";

//Dictionary constants associated with Getting Key List
var UPLOAD_KEY="uploadkey";
var ACTUATION_KEY="actuationkey";

var  oneSECOND=1000;


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
var ID_DATE_TIME_ACTUATE="#date_time";
var ID_TIME_ACTUATE="#attime";
var ID_FROMTIME_ACTUATE="#fromtime";
var ID_TOTIME_ACTUATE="#totime";

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
var MAX_LENGTH_USERNAME=20;
var MIN_LENGTH_PASSWORD=8;
var MAX_LENGTH_PASSWORD=20;
var MIN_VALUE_CHANNEL_SAMPLING_PERIOD=1;
var MAX_VALUE_CHANNEL_SAMPLING_PERIOD=100;
var MIN_VALUE_SENSOR_ID=1;
var MAX_VALUE_SENSOR_ID=100;
var MAX_VALUE_THRESHOLD=1800;   // 30 minutes
var MIN_LENGTH_GUARDRULE_NAME=5;
var MAX_LENGTH_GUARDRULE_NAME=20;
var MIN_LENGTH_GUARDRULE_DESC=5;
var MAX_LENGTH_GUARDRULE_DESC=100;
var MIN_LENGTH_VPDS_NAME=5;
var MAX_LENGTH_VPDS_NAME=20;
var MIN_LENGTH_VPDS_KEY=8;
var MAX_LENGTH_VPDS_KEY=32;
var MIN_LENGTH_VPDS_URL=20;
var MAX_LENGTH_VPDS_URL=40;


//Dictionary elements associated with Repository information
var REPOSITORY_NAME="name";
var REPOSITORY_URL="URL"




//URL's ------------------------------------------------------------------------
//Note that this needs to be modified when on LAN etc to http://192.168.1.122:9000
//############# DEVICE  ###################################
var URL_UI_SERVER="http://"+document.location.hostname+":"+document.location.port+"/";

// POST requests

var URL_LOGIN_USER="login";
var URL_REGISTER_USER="register";
var URL_LIST_USERS="listallusers";

var URL_SHARE_DEVICE="sharedevice";
var URL_VIEW_SHARED_DEVICES="viewsharedevices";
var URL_OP_CLASSIFIER="opclassifier";
var URL_LIST_SHARED_DEVICES="listsharedevices";

var URL_ADD_DEVICE="adddevice";
var URL_DELETE_DEVICE="deletedevice";
var URL_EDIT_DEVICE="editdevice";
var URL_FIND_DEVICE="finddevice";
var URL_ADD_DEVICE_TEMPLATE="adddevicetemplate";
var URL_DELETE_DEVICE_TEMPLATE="deletedevicetemplate";
var URL_FIND_DEVICE_TEMPLATE="finddevicetemplate";
var URL_ACTUATE_DEVICE="actuatedevice";

var URL_ADD_GUARD_RULE="addguardrule";
var URL_DEL_GUARD_RULE="delguardrule";
var URL_GET_GUARD_RULE="getguardrule";
var URL_EDIT_GUARD_RULE="editguardrule";
var URL_LIST_GUARD_RULE="listallguardrules";

var URL_ASSOC_ADD_GUARD_RULE="addassocguardrule";
var URL_ASSOC_DEL_GUARD_RULE="delassocguardrule";
var URL_ASSOC_GET_GUARD_RULE="getassocguardrule";
var URL_ASSOC_GUARD_RULE="assocguardrule";
var URL_ASSOC_LIST_GUARD_RULE="listassocguardrules";

var URL_REGISTER_USER=URL_UI_SERVER+"register";
var URL_QUERY_DATA="querydata2";
var URL_DOWNLOAD_DATA="downloadata";
var URL_GET_DEVICE="getdevice";
var URL_LIST_ALL_DEVICES="listalldevices";
var URL_LIST_ALL_VPDS_DEVICES="listallvpdsdevices";
var URL_LIST_ALL_DEVICE_TEMPLATES="listalldevicetemplates";
var URL_LIST_ACTUATE_REQUEST="listactuationrequests";
var URL_CANCEL_ACTUATE_REQUEST="cancelactuationrequests";

var URL_LIST_TASKLET="listasklet";

var URL_REGISTER_VPDS="registerVPDSToBroker";
var URL_GET_VPDS_DETAILS="getvpdsdetails";
var URL_VPDS_LIST="listvpds";
var URL_RELOGIN="relogin";


// GET Requests

var URL_GET_SECRET_KEY="getsecretkey"
var URL_GET_VPDS_INFO="getVPDSInfo"
var URL_GET_USER_LIST="getuserlist"

var URL_HOME="home";
var URL_VPDSHOME="managehome";

// VO specific user
var URL_HOME_VO="homevo";
var URL_REG_VPDS="registervpds";
var URL_MANAGE_VPDS="managevpds";
var URL_SHARED_DEVICES="sharedevices";
var URL_SHARE="share";
var URL_DEVICES="device";
var URL_ACTUATE="actuate";
var URL_PRESENCE_ACTUATE="presenceactuate";
var URL_GUARD_RULE="guardrule";
var URL_GENERATE_SECRET_KEY="generatesecretkey";
var URL_VISUALIZATION="display";
var URL_TASKLETS="createtasklet";

// Unused pages
var URL_REPOSITORY_INFO="repository";
var URL_GET_REPOSITORY_INFO="getrepositoryinfo";
var URL_SOUNDINPUT_CONTROLLER="soundinput";
var URL_SPEAK_INPUT = "speak";

// User specific
var URL_HOME_USER="homeuser";
var URL_USER_ACTUATE="useractuate";
var URL_USER_PRESENCE_ACTUATE="userpresenceactuate";

var URL_LOGOUT_USER="logout";





    
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
