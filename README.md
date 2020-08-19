# cochintravels-util
Utility Springboot app which supports cochintravels client app

## Encrypted Sensitive files
1. Decrypt application.properties before starting application.<br>
`openssl enc <cipher> -d -pbkdf2 -in src/main/resources/application.properties.enc -out src/main/resources/application.properties`
2. Encrypt application.properties before committing to git.<br>
`openssl enc <cipher> -salt -pbkdf2 -in src/main/resources/application.properties -out src/main/resources/application.properties.enc`