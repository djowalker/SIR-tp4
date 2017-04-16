export interface Person {
  id: number;
  name: string;
  surname: string;
  email: string;
  homes: Home[];
  friends: Friendship[];
}

export interface Home {
  id: number;
  adresse: string;
  ville: string;
  rooms: number;
  surface: number;
  devices: IntelligentDevice[];
}

export interface Friendship {
  id: number;
  idMe: number;
  idFriend: number;
}
export interface IntelligentDevice {
  id: number;
  reference: string;
  consommationAvg: number;
  type: string;
}

export class Heater implements IntelligentDevice {
  id: number;
  reference: string;
  consommationAvg: number;
  source: string;
  type: string;
}

export class ElectronicDevice implements IntelligentDevice{
  id: number;
  reference: string;
  consommationAvg: number;
  name: string;
  type: string;
}

export interface Friend {
  id: number;
  me: Person;
  friend: Person;
}