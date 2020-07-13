job('GitHub-Code') {
	triggers {
        upstream('test', 'SUCCESS')
    }
    scm {
        github('ramwadhwa1031/DevopsHw.git', 'master')
    }
    steps {
       shell('sudo cp * -v /task6')
    }

}
