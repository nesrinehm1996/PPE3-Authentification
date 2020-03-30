node{
         Stage 'SCM checkout'{
                  git 'https://github.com/Raouagarati101/PPE3-Authentification'
         }
         Stage 'compile stage'{
         def mvnHome = tool name: 'maven-3.6.3', type: 'maven'
                  sh "${mvnHome}/bin/mvn package"
         }
}

