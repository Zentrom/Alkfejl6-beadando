import { UserDTO } from './userdto';

export enum Status {
    PENDING, ACCEPTED, DECLINED
}

export class FriendRequest {
    constructor(
        public id: number,
        public requester: UserDTO,
        public requestee: UserDTO,
        public status: Status
    ) {}
}