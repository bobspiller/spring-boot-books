#!/bin/bash

BASEDIR=$(dirname $0)

if [ -f ${BASEDIR}/setenv.sh ]; then
    . ${BASEDIR}/setenv.sh
fi

cd ${BASEDIR}

java -jar books-{{ app_version }}.jar