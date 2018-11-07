# santanu-contact-book-plivo
Assignment by Plivo Communication Pvt. Ltd.

Deployed on heroku and added Swagger: 
* https://santanu-contact-book.herokuapp.com
* [Swagger UI](https://santanu-contact-book.herokuapp.com/swagger-ui.html)

## APIs

**Create**: 
* POST /api/create
 * Content-Type : application/json
 * example body:
```
{
  "email" : "email@something.com",
  "name" : "my name",
  "phone" : 192839
}
```

**Search**: 
* GET /api/search/by_name?name=*name*
* GET /api/search/by_email?email=*email*

**Update**
* PUT /api/update/by_email?email=*email*

**Delete**
* DELETE /api/delete/by_email?email=*email*

Basic authentication added. Use following credentials:

**username**: plivo

**password**: password
