echo 'Use pkg as a temporary directory'

rm -rf pkg

rake build
cd pkg

gem install -i ./dir_to_be_jarred model-gem --no-rdoc --no-ri model-gem-1.0.gem
jar cf model-gem-1.0-SNAPSHOT.jar -C dir_to_be_jarred .

mkdir -p $HOME/.ivy2/local/com.drsquidop/scala-jruby-activerecord-model-gem/1.0-SNAPSHOT/jars
cp model-gem-1.0-SNAPSHOT.jar $HOME/.ivy2/local/com.drsquidop/scala-jruby-activerecord-model-gem/1.0-SNAPSHOT/jars/scala-jruby-activerecord-model-gem.jar

mkdir -p $HOME/.ivy2/local/com.drsquidop/scala-jruby-activerecord-model-gem/1.0-SNAPSHOT/ivys
cp ../ivy.xml $HOME/.ivy2/local/com.drsquidop/scala-jruby-activerecord-model-gem/1.0-SNAPSHOT/ivys/ivy.xml

echo 'Published "com.drsquidop" % "scala-jruby-activerecord-model-gem" % "1.0-SNAPSHOT"'
