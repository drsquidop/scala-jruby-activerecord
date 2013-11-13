require 'model_gem'
require 'my_model'

module MyModelSpec
  describe MyModel do
    before(:each) do
      MyModel.delete_all
    end

    it "Creates new model" do
      m = User.create(name: "Joe")
    end
  end
end
