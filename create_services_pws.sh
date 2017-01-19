cf cups emc-forum-service-registry -p '{"uri":"http://emc-forum-eureka.cfapps.io"}'
cf cups emc-forum-config-server -p '{"uri":"http://emc-forum-config.cfapps.io"}'
cf cs cloudamqp lemur emc-forum-rabbitmq
cf cs cleardb spark emc-forum-answer-db

cf map-route emc-forum-repository cfapps.io --hostname emc-forum --path userAnswers

