import { User } from './user';

export class Present {
    public id: number;

    constructor(
        private name: string,
        private price: number,
        private link: string,
        private hidden: boolean,
        private user: User = null,
    ) {}

    public getId(): number {
        return this.id;
    }

    public getName(): string {
        return this.name;
    }

    public getPrice(): number {
        return this.price;
    }

    public getLink(): string {
        return this.link;
    }

    public isHidden(): boolean {
        return this.hidden;
    }

    public getUser(): User {
        return this.user;
    }

}