cf cups pivotal-meetup-apj-service-registry -p '{"uri":"http://pivotal-meetup-apj-eureka.cfapps.io"}'
cf cups pivotal-meetup-apj-config-server -p '{"uri":"http://pivotal-meetup-apj-config.cfapps.io"}'
cf cs cloudamqp lemur pivotal-meetup-apj-rabbitmq
cf cs cleardb spark pivotal-meetup-apj-answer-db

cf map-route pivotal-meetup-apj-repository cfapps.io --hostname pivotal-meetup-apj --path userAnswers

