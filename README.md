# FizzBuzzServer

Setting up the project:
Install Eclipse IDE:
If you haven't installed Eclipse, download it from the official website and install it on your machine.

Create a new Java project:
Open Eclipse and create a new Java project (File > New > Java Project). Give it a name and click "Finish".

Add required libraries (Spark and JUnit):
Right-click on the project name, select Build Path > Configure Build Path. Go to the Libraries tab, then click Add External JARs... and add the following JAR files:

spark-core-x.x.x.jar (Download Spark framework from Maven or its official website): To implement a REST server using the Spark Java framework. Spark is a lightweight web framework for Java that simplifies the creation of web applications, including RESTful APIs.
Used to create two endpoints ("/process" and "/most_used_request"):
/process: Accepts parameters (int1, int2, limit, str1, str2) and processes them according to specific logic.
/most_used_request: Retrieves statistics about the most frequently used request.
Since Spark abstracts away much of the HTTP server setup and request handling complexities, it simplifies the creation of RESTful services in Java. However, if your project doesn't specifically require Spark or a web framework, simpler solutions such as using Java's built-in HttpServer or other lightweight libraries might suffice.

junit-x.x.jar (Download JUnit library): JUnit is a widely used testing framework for Java. In the provided code, JUnit is used to create unit tests to verify the functionality of the REST server. Unit testing helps ensure that individual components of the code (such as methods or classes) work as expected in isolation.
The purpose of using JUnit in this context is to:
Test Endpoint Logic: Ensure that the endpoints ("/process" and "/most_used_request") behave as intended when receiving requests and producing responses.
Validate Request Handling: Test the processing of parameters in the "/process" endpoint to confirm that it correctly modifies the input data according to the specified requirements.
Verify Statistics Calculation: Test the calculation of the most used request and hit counts in the "/most_used_request" endpoint.

Run the Project:
Run Server class as Java Application.
Run Test class as Java Application.
