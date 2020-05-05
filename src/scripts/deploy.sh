#!/bin/bash

REPOSITORY=/home/ec2-user/app/step
PROJECT_NAME=springboot-webservice

echo "> Build 파일 복사"

cp ${REPOSITORY}/zip/*.jar ${REPOSITORY}/

echo "> 현재 구동중인 애플리케이션 pid 확인"

#현재 수행중인 스프링부트 애플리케이션의 프로세스 ID를 찾음
CURRENT_PID=$(pgrep -fl ${PROJECT_NAME} | grep jar | awk '{print $1}')

echo "> 현재 구동중인 애플리케이션 pid: $CURRENT_PID"

if [[ -z "$CURRENT_PID" ]]; then
        echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
else
        echo "> kill -15 $CURRENT_PID"
        kill -15 ${CURRENT_PID}
        sleep 5
fi

echo "> 새 애플리케이션 배포"

JAR_NAME=$(ls -tr ${REPOSITORY}/*.jar | tail -n 1)

echo "> JAR Name: $JAR_NAME"

echo "> $JAR_NAME 에 실행권한 추가"

chmod +x ${JAR_NAME} #nohup으로 실행할 수 있게 실행 권한 부여

echo "> $JAR_NAME 실행"

nohup java -jar \
        -Dspring.config.location=classpath:/application.yml,classpath:/application-real.yml,/home/ec2-user/app/application-oauth.yml,/home/ec2-user/app/application-real-db.yml \
        -Dspring.profiles.active=real \
${JAR_NAME} > ${REPOSITORY}/nohup.out 2>&1 & #nohup 실행시 CodeDeploy는 무한 대기 -> 이를 해결하기 위해 nohup.out 파일을 효준 입출력용으로 별도로 사용
