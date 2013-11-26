require 'model_gem'
require 'securerandom'

module MyModelSpec
  include ::ModelGem

  describe MyModel do
    yml = IO.read('config/database.yml')
    env = 'test'
    ModelGem.connect(yml, env)

    before(:each) do
      MyModel.delete_all
    end

    it 'MyGemModel.new with (any string) creates a record in the database' do
      name = SecureRandom.uuid
      MyModel.create(name: name)
      MyModel.all.should_not be_empty
    end

    it 'MyGemModel.new with (blank string) returns rails standard validation error' do
      m = MyModel.create(name: '')
      m.valid?.should_not be_true
      m.errors.should_not be_empty
    end

    it 'MyGemModel.find(id) returns a valid object' do
      name = SecureRandom.uuid
      m    = MyModel.create(name: name)

      m2 = MyModel.find(m.id)
      m2.id.should eq m.id
    end

    it 'MyGemModel.all returns multiple entries' do
      name = SecureRandom.uuid
      m    = MyModel.create(name: name)

      name2 = SecureRandom.uuid
      m2    = MyModel.create(name: name)

      (MyModel.all.length > 1).should be_true
    end
  end
end
