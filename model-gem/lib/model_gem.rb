require 'model_gem/version'
require 'model_gem/my_model'

require 'activerecord-jdbcpostgresql-adapter'

module ModelGem
  def self.connect(yml_configs, env)
    ActiveRecord::Base.configurations = YAML::load(yml_configs)
    ActiveRecord::Base.establish_connection(env)
  end
end
