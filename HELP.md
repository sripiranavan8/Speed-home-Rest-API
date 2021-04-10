##### (By default when you start the application database fill with basic Authentication credentials, here I'm using H2 database)
##1 - To get the access token
* url: http://localhost:8080/oauth/token
* method: post
* Authorization Credentials [
  client_id: client1 , 
  secret: secret1 ,
  username: Sripiranavan ,
  password: 12345 ,
  scope: read ,
  grant_type: password ]
  
##2 - Create a Property
* url: http://localhost:8080/properties
* method: post
* in the header set Authorization = Bearer [token]
* Property details [
  propertyName = Test Property,
  propertyType = Test Property Type,
  propertyDescription = Test Property Description ]

##3 - Update a Property
* url: http://localhost:8080/properties
* method: put
* in the header set Authorization = Bearer [token]
* Property details [
  propertyName = Test Property,
  propertyType = Test Property Type,
  propertyDescription = Test Property Description,
  propertyStatus = true/false]

##4 - Update a Property Status
* url: http://localhost:8080/properties/status
* method: put
* in the header set Authorization = Bearer [token]
* Property details [
  id = project id,
  propertyStatus = true/false]

##5 - Search properties by name or type
* url: http://localhost:8080/properties/filte
* method: get
* in the header set Authorization = Bearer [token]
* Search param details [search= your search string]

##6 - Search properties by status
* url: http://localhost:8080/properties/status
* method: get
* in the header set Authorization = Bearer [token]
* Search param details [status= true/false]

