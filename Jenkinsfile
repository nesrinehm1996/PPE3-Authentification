#!/usr/bin/env groovy
pipeline {
         agent any {
       
   
   stages {
       stage('Build') {
           steps {
                    withMaven(maven : 'maven-3.6.3'){
            
               sh 'mvn clean compile'
                    }
           }
       }
       stage('Test') {
           steps {
                    withMaven(maven : 'maven-3.6.3'){
                    
               sh 'mvn test'
           }
       }
   }
        stage('deploy') {
           steps {
                    withMaven(maven : 'maven-3.6.3'){
                    
               sh 'mvn deploy'
           }
       }
   }     
}
         }
