DataReplication by BackBlaze data storage provider.

## Data replication
* Data uploaded onto Backblaze's data center is sharded into 17 data shards plus three parity shards for each file. (Parity servers are there to recalculate the file if some piece of the file is missing)
* Parity shard bits are computed by the Reed–Solomon error correction algorithm. 
* The shards are stored in 20 storage pods, each in a separate cabinet to increase resilience to a power loss to an entire cabinet. Backblaze states that its Vault architecture is designed with 99.99999% annual durability.

The idea is to break the data into multiple pieces (i.e maintain different shards) and then store it accross many servers in the data center. When a server goes down for maintainence, this data can still be retrieved and make them fully available.

![reed solomon encoding](https://user-images.githubusercontent.com/6800366/37762274-d3445332-2de1-11e8-870c-4b6c375291af.PNG)


## More of the use case example here:
https://www.youtube.com/watch?v=jgO09opx56o
