project based on Springboot mockito junit </br>
integrate sendgrid for emails</br>
notes: need to set the your sendgrid key</br>

the JSON template for use for post is</br>
{</br>
	"sender": "swoecwang10@gmail.com",</br>
	"receiver":"swoecwang10@gmail.com",</br>
	"title":"ccurate and reliable news stories are more important now than ever.",</br>
	"body": " Two Waitemata .",</br>
	"cc":["swoecwang@gmail.com","swoec.wang@gmail.com"]</br>
}</br>
to run the project use: mvn spring-boot:run </br>

Link: http://localhost:8081/sendgrid?enrich=true

add a photo to the letter to enrich the content.


