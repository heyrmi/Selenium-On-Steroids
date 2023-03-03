#!/bin/bash
ALLURE_RESULTS_DIRECTORY='allure-results'

ALLURE_SERVER='http://localhost:5050'

PROJECT_ID='selenium-on-steroids'

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
FILES_TO_SEND=$(ls -dp $DIR/$ALLURE_RESULTS_DIRECTORY/* | grep -v /$)
if [ -z "$FILES_TO_SEND" ]; then
  exit 1
fi

FILES=''
for FILE in $FILES_TO_SEND; do
  FILES+="-F files[]=@$FILE "
done

set -o xtrace
echo "------------------SENDING-RESULTS------------------"
curl -X POST "$ALLURE_SERVER/allure-docker-service/send-results?project_id=$PROJECT_ID" \
    -H 'Content-Type: multipart/form-data' \
    $FILES -ik
