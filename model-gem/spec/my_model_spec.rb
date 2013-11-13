require 'model_gem'
require 'my_model'

module MyModelSpec
  describe MyModel do
    before(:each) do
      MyModel.delete_all
    end

    it "Creates new model for non empty name" do
      MyModel.create(name: "Joe")
      MyModel.all.should_not be_empty
    end

    it "Does not create new model for empty name" do
      m = MyModel.create
      m.valid?.should_not be_true
    end
  end
end
