node {
    try {
        stage ("Begin") {
            steps {
                sh "apt install maven"
            }
        }

        stage ("Build") {
            steps {
                sh 'mvn -f demojenkins/pom.xml clean package'
            }
        }

        stage ("Test") {
            steps {
                step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'])
            }
        }

        stage ("Deloy") {
            steps {
                echo 'Deloy ....'
            }
        }
    } catch (e) {
        throw e
    } finally {
        def currentResult = currentBuild.result ?: 'SUCCESS'

        def DATE = sh(returnStdout: true, script: "date +'%d-%m-%y'")

        def NOTIFICATION_SUCCESS = "'{\"text\":\"NAM == SUCCESS, ${DATE}\"}'"
        def NOTIFICATION_FAILTURE = "'{\"text\":\"NAM == FAILTURE ${DATE}\"}'"
        def API_A = "https://hooks.slack.com/services/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"
        

        if (currentResult == 'SUCCESS') {
            sh "curl -X POST -H 'Content-type: application/json' --data ${NOTIFICATION_SUCCESS} ${API_A}"
        } else {
            sh "curl -X POST -H 'Content-type: application/json' --data ${NOTIFICATION_FAILTURE} ${API_A}"
        }
    }
}