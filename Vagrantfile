Vagrant.configure(2) do |config|
  config.vm.box = "bento/centos-7.3"
  config.vm.provider "virtualbox" do |v|
    v.memory = 2048
    v.cpus = 2
  end

  # Use :ansible or :ansible_local to
  # select the provisioner of your choice
  config.vm.provision :ansible do |ansible|
    ansible.playbook = "target/classes/ansible/playbook.yml"
  end

  config.vm.network "forwarded_port", guest: 8080, host: 8080
  config.vm.network "forwarded_port", guest: 8081, host: 8081
  config.vm.provision "file", source: "src/main/vagrant/vm-bash-profile.sh", destination: ".bash_profile"
  config.vm.provision "file", source: "~/.gitconfig", destination: ".gitconfig"
  config.vm.provision "file", source: "~/.vimrc", destination: ".vimrc"
  config.vm.synced_folder "~/Downloads/", "/home/vagrant/distros"
  config.vm.synced_folder  "~/.m2/", "/home/vagrant/.m2"
end
