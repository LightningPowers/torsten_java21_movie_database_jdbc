use movies;

-- Checks that added releasedate is from 2030 or earlier
drop trigger if exists ins_releaseyear;
delimiter $$
create trigger ins_releaseyear before insert
	on releasedate 
    for each row
    begin
		if new.releasedate >= 2030 then
		signal sqlstate '45000'
        set message_text = 'Can only add a release years prior to 2030';
        end if;
    end;
    $$
