if [ -z "$1" ] ; then
    java -jar target/parking-lot-1.0-SNAPSHOT.jar
    exit 1

else
	java -jar target/parking-lot-1.0-SNAPSHOT.jar $1

fi

