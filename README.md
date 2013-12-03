cookapp-cloud
=============

Server background for future Android App.
current instance - 
cookcloud.jelastic.neohost.net/

example  - cookcloud.jelastic.neohost.net/rest/barcode/0000040102078

setup ENV
1 download lombok plugin for IDE http://projectlombok.org/features/index.html - 
2 setup local mysql. import database. 
login root passw 123456
3 download enunciate-1.26.2 - generation of docs for REST services
setup env vars. ENUNCIATE_HOME
4 download mongodb.
start mongodb

change config.xml inside maven dir -
add 

<profile>
            <id>jelastic</id>
            <activation>
                <activeByDefault>true</activeByDefault>
            </activation>
            <properties>
                <jelastic.username>[insert your Jelastic username]</jelastic.username>
                <jelastic.password>[insert your Jelastic password]</jelastic.password>
            </properties>
</profile>