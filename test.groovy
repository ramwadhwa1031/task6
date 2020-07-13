job('GitHub-Code') {
	triggers {
        upstream('test', 'SUCCESS')
    }
    scm {
        github('ramwadhwa1031/task6', 'master')
    }
    steps {
       shell('sudo cp * -v /task6')
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

