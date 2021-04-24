# GolfWithMeService
Service project for the GolfWithMe Application

Hit the app att https://golfwithmeservice.herokuapp.com

Commit to github - feature branch
On PR to main, run build checks (should do that on any push)
on PR to main, kicks off a workflow that deploys to heroku. LFG

### docker
```docker image build -t "image" .```<br>
```docker run -d -p 8080:8080 --name container image:latest```

###heroku buildpacks
```https://stackoverflow.com/questions/47446480/how-to-use-google-api-credentials-json-on-heroku```

```https://github.com/gerywahyunugraha/heroku-google-application-credentials-buildpack```

```https://elements.heroku.com/buildpacks/buyersight/heroku-google-application-credentials-buildpack```
