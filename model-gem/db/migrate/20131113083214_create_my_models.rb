class CreateMyModels < ActiveRecord::Migration
  def up
    create_table :my_models do |t|
      t.string :name
    end
  end
end
