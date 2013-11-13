class CreateMyModel < ActiveRecord::Migration
  def up
    create_table :my_model do |t|
      t.string :name
    end
  end
end
