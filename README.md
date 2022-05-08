To create a facade application GATEWAY that provides currency data to 
different types of customers.
Functional specification 
The system consists of the following components:
1. Collect current data about the currencies provided by **https://fixer.io/** to store in the 
relational database. Updating of the data should occur on a redefined 
time interval, by means of a configuration
2. Provides two public REST APIs for external EXT_SERVICE_1 and EXT_SERVICE_2 running services 
with Content-type application/json and application/xml respectively. The returned information contains 
data from fixer.io, the structure of the response is of your choice, but in accordance with Content-type
supported by the external service. Details and examples follow:


Processing this riquest requires checking for duplicate riquests with the given id. In case 
duplication, the system returns an error. The client field, identifies the end client. For 
normal execution of the riquest, the response contains the last data entered into the system 
for the respective currency

This riquest requires checking for duplicate riquests again. The repo contains a list of 
accumulated data for the corresponding currency during the specified period. The time interval is 
is interpreted as hours and is an integer.


The id attribute in the command tag, uniquely identifies the riquest. The consumer attribute, to 
be interpreted as the id of the end user. Check for duplicate riquests should also 
be implemented.
3. Collect uniform statistics (service name/id - EXT_SERVICE_X, request id, 
time (UTC), end client id) in a relational database, regarding the incoming riquests from
EXT_SERVICE_1 and EXT_SERVICE_2
4. To forward, via a web socket (RabbitMQ), the unified information about incoming riquests. 
The exchange name is redefined in the configuration.


Test Examples:

**/json_api/current
{
 "requestId": "b89577fe-8c37-4962-8af3-7cb89a245160",
 "timestamp": 1586335186721, // UTC 
 "client": "1234",
“currency”:”EUR”
}**

**/json_api/history
{
 "requestId": "b89577fe-8c37-4962-8af3-7cb89a24q909",
 "timestamp": 1586335186721,
 "client": "1234",
“currency”:”EUR”,
 "period": 24
}**

**xml_api/command:
Current data
**<command id="1234" >
<get consumer="13617162" >
<currency>EUR</currency>
</get>
</command>**

**Statistic data
<command id="1234-8785" >
<history consumer="13617162" currency=“EUR” period=”24” />
</command>****
