cookapp-cloud
=============

Server background for future Android App.
current instance - 
cookcloud.jelastic.neohost.net/

example  - cookcloud.jelastic.neohost.net/rest/barcode/0000040102078

setup ENV
1 download lombok plugin for IDE http://projectlombok.org/features/index.html - 
2 download enunciate-1.26.2 - generation of docs for REST services
setup env vars. ENUNCIATE_HOME
3 Download and install Maven http://maven.apache.org/download.cgi
change settings.xml inside maven dir -
add 
4 For tomcat http://stackoverflow.com/questions/8701307/the-absolute-uri-http-java-sun-com-jsp-jstl-core-cannot-be-resolved-in-either



    <profile>
            <activation>
                <activeByDefault>true</activeByDefault>
            </activation>
            <properties>
                <mongo.url>ds041248.mongolab.com:41248/mongofood</mongo.url>
                <mongo.login>admin</mongo.login>
                <mongo.password>123456</mongo.password>
              	<db.url>jdbc:mysql://db4free.net:3306/cookcloud</db.url>
                <db.login>cookcloud</db.login>
                <db.password>123456</db.password>
            </properties>
    </profile>

*    for local mysql use login root passw 123456

tst
