For Both SpringBootApplications PhoneProducer/PhoneConsumer:
I have commited the source files of my project, plus the pom.xml for Maven build.
I have compiled and run the code with jdk1.8.0_131 using rabbitmq_server-3.6.14

#PhoneProducer
It produces phone messages with a scheduler.
PhoneNumberUtils is the chosen library for generating international numbers.
It also includes a RestController(PhoneController) so as to accept phone numbers by a real world client.
A direct exchange is used.


#PhoneConsumer
Consumer that counts the phone messages received.
A RestController(PhoneController) is used to allow user retrieve the grouped data.

Unit tests are included.

