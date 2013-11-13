require 'active_record'

class MyModel < ActiveRecord::Base
  validates :name, presence: true
end
