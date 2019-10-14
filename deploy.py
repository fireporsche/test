# -*- coding:UTF-8 -*-

from fabric.api import *
from fabric.contrib.files import exists
import os

HOSTS = ['haishu@192.168.32.135']
env.password='Hlw..2019'

APP_NAME = 'portal-be-0.0.1.jar'
PROJECT_NAME = 'social-mall-platform-weixin-be'
JAR_PATH = '/home/hl/.jenkins/workspace/' + PROJECT_NAME + '/build/libs/' + APP_NAME
APP_PATH = '/home/haishu/app/be/portal'
PROPERTIES_FILE = '/home/hl/devops/jenkins-local/backend/' + PROJECT_NAME + '/application.properties'

def scripts():
    put(JAR_PATH, APP_PATH)
    put(PROPERTIES_FILE, APP_PATH)
    if exists(APP_PATH + '/app.pid'):
        with settings(warn_only=True):
            run('kill -9 $(cat ' + APP_PATH + '/app.pid)')
    with cd(APP_PATH):
        run('$(nohup java -Xmx2g -jar ' + APP_NAME + ' --spring.config.location=application.properties >& /dev/null < /dev/null &) && sleep 1')

def deploy(hosts):
    execute(scripts, hosts=hosts)

if __name__=="__main__":
    deploy(HOSTS)

