job('GitHub-Code') {
	triggers {
        upstream('test', 'SUCCESS')
    }
    scm {
        github('ramwadhwa1031/task6', 'master')
    }
    steps {
       shell(''' sudo cp * -v /task6
       sudo docker build -t d1031/web-server:v1 /task6/
       sudo docker push d1031/web-server:v1
       ''')
    }

}
job('Deployment') {
	triggers {
        upstream('GitHub-Code', 'SUCCESS')
    }
    
    steps {
       shell(''' fullfilename="/task6/*.html"
			filename=$(basename "$fullfilename")
			ext="${filename##*.}"
			echo $ext
           
			if [ $ext == html ];
			then
			   if   kubectl get deployment | grep webserver 
			   then
			      kubectl create -f /task6/deployment.yml
				else
				  echo "creating"
				 fi
			else
			   echo "everything is wokring"
			fi	 
				 
			''')
    }

}

