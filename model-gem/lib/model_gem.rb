require 'activerecord-jdbcpostgresql-adapter'

ActiveRecord::Base.configurations = YAML::load(IO.read('config/database.yml'))
ActiveRecord::Base.establish_connection('development')
