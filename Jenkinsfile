pipeline {
    agent {
        étiquette " maître "
    }
    outils {
        // Remarque: cela doit correspondre au nom d'outil configuré dans votre instance jenkins (JENKINS_URL / configureTools /)
        maven " apache-maven-3.6.3 "
    }
    environnement {
        // Cela peut être nexus3 ou nexus2
        NEXUS_VERSION  =  " nexus3 "
        // Cela peut être http ou https
        NEXUS_PROTOCOL  =  " http "
        // Où fonctionne votre Nexus
        NEXUS_URL  =  " 127.0.0.1:8081 "
        // Référentiel où nous téléchargerons l'artefact
        NEXUS_REPOSITORY  =  " nexus-test "
        // Identifiant Jenkins pour s'authentifier auprès de Nexus OSS
        NEXUS_CREDENTIAL_ID  =  " nexus-credentials "
    }
    étapes {
        étape ( " code clone " ) {
            pas {
                script {
                    // Clonons la source
                    git ' https://github.com/Raouagarati101/PPE3.Authentification.git '
                }
            }
        }
        stage ( " mvn build " ) {
            pas {
                script {
                    // Si vous utilisez Windows, vous devez utiliser l'étape "bat"
                    // Les tests unitaires étant hors de portée, nous les ignorons
                    sh " package mvn -DskipTests = true "
                }
            }
        }
        stade ( " publier sur nexus " ) {
            pas {
                script {
                    // Lire le fichier xml POM en utilisant l'étape 'readMavenPom', cette étape 'readMavenPom' est incluse dans: https://plugins.jenkins.io/pipeline-utility-steps
                    pom = fichier readMavenPom : " pom.xml " ;
                    // Trouver un artefact construit dans le dossier cible
                    filesByGlob = findFiles ( glob : " target / *. $ { pom.packaging } " );
                    // Imprimer des informations sur l'artefact trouvé
                    echo " $ { filesByGlob [0] .name }  $ { filesByGlob [0] .path }  $ { filesByGlob [0] .directory }  $ { filesByGlob [0] .length }  $ { filesByGlob [0] .lastModified } "
                    // Extraire le chemin du fichier trouvé
                    artifactPath = filesByGlob [ 0 ] . chemin;
                    // Assigner à une réponse booléenne vérifiant si le nom de l'artefact existe
                    artifactExists = fileExists artifactPath;
                    if (artifactExists) {
                        echo " *** Fichier: $ { artifactPath } , groupe: $ { pom.groupId } , emballage: $ { pom.packaging } , version $ { pom.version } " ;
                        nexusArtifactUploader (
                            nexusVersion : NEXUS_VERSION ,
                            protocole : NEXUS_PROTOCOL ,
                            nexusUrl : NEXUS_URL ,
                            groupId : pom . groupId,
                            version : pom . version,
                            référentiel : NEXUS_REPOSITORY ,
                            credentialsId : NEXUS_CREDENTIAL_ID ,
                            artefacts : [
                                // Artefact généré tel que les fichiers .jar, .ear et .war.
                                [ artifactId : pom . artifactId,
                                classificateur : ' ' ,
                                fichier : artifactPath,
                                type : pom . emballage],
                                // Permet de télécharger le fichier pom.xml pour plus d'informations sur les dépendances transitives
                                [ artifactId : pom . artifactId,
                                classificateur : ' ' ,
                                fichier : " pom.xml " ,
                                type : " pom " ]
                            ]
                        );
                    } else {
                        erreur " *** Fichier: $ { artifactPath } , introuvable " ;
                    }
                }
            }
        }
    }
}
