require 'model_gem/version'
require 'model_gem/my_model'

require 'activerecord-jdbcpostgresql-adapter'

module ModelGem
  def self.connect(configs = {}, env = 'development')
    configs = YAML::load(IO.read('config/database.yml')) if configs.empty?
    ActiveRecord::Base.configurations = configs
    ActiveRecord::Base.establish_connection(env)
  end
end
