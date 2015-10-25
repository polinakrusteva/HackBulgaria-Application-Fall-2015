import json
import os


class Dependency:

    def __init__(self):
        self.packages = self.load("all_packages.json")
        self.dependencies = self.load("dependencies.json")
        self.installed = []
        for root, dirs, files in os.walk("installed_modules"):
            self.installed.extend(files)

    def traverse(self, packages, start, installed):
        if(start in self.installed):
            print("{} is already installed".format(start))
        print("Installing {}".format(start))
        listdep = self.packages[start]
        for dependency in listdep:
            print("In order to install {} we need {}".format(start, dependency))
            self.traverse(self.packages, dependency, self.installed)
        open("installed_modules/{}".format(start), 'w')
        return

    @staticmethod
    def load(filename):
        with open(filename, "r") as f:
            contents = f.read()
            data = json.loads(contents)
            return data

    def resolve(self):
        for dependency in self.dependencies["dependencies"]:
            self.traverse(self.packages, dependency, self.installed)
