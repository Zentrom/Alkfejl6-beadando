export class WishList {
    public id: number;

    constructor(
        private title: string
    ) {}

    public getId(): number {
        return this.id;
    }

    public getTitle(): string {
        return this.title;
    }
}