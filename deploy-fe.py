# -*- coding:UTF-8 -*-

from fabric.api import *
from fabric.contrib.files import exists
import os

HOSTS = ['haishu@192.168.32.135']
env.password='Hlw..2019'

PROJECT_NAME = 'social-mall-platform-weixin-fe'
PROJECT_PATH = '/home/hl/.jenkins/workspace/' + PROJECT_NAME
APP_PATH = '/home/haishu/app/fe/portal'

def scripts():
    run('rm -Rf ' + APP_PATH + '/*')
    put(PROJECT_PATH + '/*', APP_PATH)

def deploy(hosts):
    execute(scripts, hosts=hosts)

if __name__=="__main__":
    deploy(HOSTS)

