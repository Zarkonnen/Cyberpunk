### Simulation
The idea is basically that there are Tiles and People, and they can have Interactions that modify them, and have certain preconditions. The NPCs then basically get a bunch of ranked rules that are all of the form of `IF condition THEN PATH TO tile_type` and `IF condition THEN PERFORM interaction`. The player gets to choose theirs, and we will attempt to slap a decent UI over them as much as possible.

The question that arises is how to implement Interactions. The easiest is for each Interaction to be a class implementing something like:

    public interface Interaction {
      public boolean available(Person actor, Person target);
      public String description(Person actor, Person target);
      public String run(Person actor, Person target);
    }

Alternatively, the interaction could be more data-driven, but that is probably too complex for the scope.

### Gameplay
Gameplay basically consists of:
* scavenging electronic parts
* hacking into security systems
* buying and selling stuff
* doing missions of the form `OBTAIN item_type`.

What I would like to achieve is emergent multiple paths to victory. Let's say you get a mission to acquire a Fruktel 900c Neural Interface. You can: scavenge around abandoned offices hoping to find one, find someone who has one, hack into their headware and crudely rip out the interface, use your tools to find a shipment of them, break into the store room to get it. Or, uh, just buy one from Bob The Obvious Fence, who likes you and gives you decent prices because you did some favors for him.

Also, your success in earlier missions may make later ones harder, as it turns out you were working for the bad guys...

### Plot
The vague idea is that victory involves defeating an Evil Plot by an Evil Corporation run by an expert system. (Not an AI proper. It has not gone mad as such, it just was not... too cleverly programmed in terms of taking into account the human factor in its problem-solving strategies. As written in my "Cyberpunk is now" manifesto and given the 20 mins into the future thing, there genuinely is no AI. Just a lot of people who think that heuristics and analytics and drones and whatnot are The Way Forward, because they have all the philosophical sophistication of a ferret.)
