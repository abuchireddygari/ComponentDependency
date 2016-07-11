# ComponentDependency
POC for component installation checking the dependency for remove and adding dependency for insert
Implements components installer (implement the following methods: MakeDependency(component1, component2) // makes component1 dependent on component2
Install(component1) // installs component1 and components it is dependent upon (if they're not installed already)
Remove(component2) // removes component1 and components it is dependent upon if they're not used by other installed components.
Note: component1 can be dependent on component2 and component3; and component4 can be dependent on component2
