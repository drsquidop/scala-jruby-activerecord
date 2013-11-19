lib = File.expand_path('../lib', __FILE__)
$LOAD_PATH.unshift(lib) unless $LOAD_PATH.include?(lib)
require 'model_gem/version'

Gem::Specification.new do |s|
  s.name        = 'model-gem'
  s.version     = ModelGem::VERSION
  s.authors     = ["drsquidop"]
  s.email       = 'drsquidop@drsquidop.com'
  s.description = 'scala-jruby-activerecord'
  s.summary     = 'scala-jruby-activerecord'
  s.homepage    = 'https://github.com/drsquidop/scala-jruby-activerecord'
  s.license     = 'Apache'

  s.files         = Dir.glob('lib/**/*.rb')
  s.require_paths = ['lib']

  s.add_development_dependency 'bundler', '~> 1.3'
  s.add_development_dependency 'rake'
end
