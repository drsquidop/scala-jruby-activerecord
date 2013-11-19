require 'active_record'

module ModelGem
  class MyModel < ActiveRecord::Base
    validates :name, presence: true
  end
end
