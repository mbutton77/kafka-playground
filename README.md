<h1>Prerequisites</h1>

You have to have a fully running Kafka cluster (even if there's only one node) and a <a href="https://www.confluent.io/download/">Confluent schema registry</a> running as well.

<h1>Build</h1>

Use Maven to build the project.
It will generate the Avro Java stub from the Avro schema.

<h1>Run</h1>

Run the Java file AvroConsumer and then AvroProducer to send and receives the messages
