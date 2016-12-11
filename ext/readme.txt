This folder contains the computations which are packed to a jar
The extension should include the maven-project service-provider.
The implementetion must implements the serv iceloader interface.
Inside the resources folder place META-INF/services and the file de.tiq.solutions.livemon.contentdivider.worker.ServiceLoaderInterface
which enclosed the full qualified name of the Class which implements the interface od service-provider