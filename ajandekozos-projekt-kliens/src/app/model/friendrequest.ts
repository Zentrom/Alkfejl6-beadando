export enum Status {
    PENDING, ACCEPTED, DECLINED
}

export class FriendRequest {
    constructor(
        public id: number,
        public requesterId: number,
        public requesteeId: number,
        public requesterName: string,
        public requesteeName: string,
        public status: Status
    ) {}
}